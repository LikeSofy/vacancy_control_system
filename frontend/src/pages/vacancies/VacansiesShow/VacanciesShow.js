import * as React from "react";
import {
    useLocaleState,
    Datagrid,
    TextField,
    Show,
    RichTextField,
    useRefresh,
    SimpleShowLayout,
    ReferenceField,
    NumberField,
    ReferenceManyField,
    usePermissions,
    DateField,
    useRecordContext,
    useTranslate,
    useShowContext
} from 'react-admin';
import {axiosInstance} from "../../../dataProvider"
import Button from '@mui/material/Button';
import { useNavigate } from "react-router-dom";


const SubscribeButton = (props) => {
    const refresh = useRefresh();
    const record = useRecordContext(props);
    const translate = useTranslate();
    const navigate = useNavigate();

    const subscribe = (id) => {
        axiosInstance.get(`/api/v1/interviews/${id}/subscribe`)
            .then((response) => {
                refresh()
                return response.data
            }).catch(error => {
                if (error.response.status === 401 || error.response.status === 403) {
                    navigate("/login")
                }
            })
    }

    if (!record)
        return null

    return (
        <Button 
            variant="text" 
            style={{float: "right"}} 
            onClick={() => subscribe(record.id)}
        >
            {translate("resources.interviews.buttons.subscribe")}
        </Button>
    )
}

const Interviews = ({permissions}) => {
    const context = useShowContext();

    if (context.isLoading)
        return (<div>Waiting for data...</div>)

    return (
        <ReferenceManyField 
            label="resources.vacancies.fields.interviews" 
            reference="interviews" 
            target="author_id" 
            filter={{
                busyByUser: false, 
                vacancyId: context.record.id
            }}
        >
            <Datagrid bulkActionButtons={null}>
                <DateField source="start" showTime={true}/>
                <ReferenceField reference="users" source="interviewerId">
                    <>
                        <TextField source="name" style={{marginRight: "10px"}}/>
                        <TextField source="surname" />
                    </>
                </ReferenceField >
                <SubscribeButton reference="id" permissions={permissions}/>
            </Datagrid>
        </ReferenceManyField>
    )
}

const VacanciesShow = () => {
    const { isLoading, permissions } = usePermissions()
    const [locale] = useLocaleState();

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_INTERVIEWER")
        return (
            <Show>
                <SimpleShowLayout>
                    <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                    <RichTextField label="resources.vacancies.fields.description" source={`description.${locale}`} />
                    <ReferenceField source="scheduleId" reference="schedules">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField>
                    <ReferenceField source="typeEmploymentId" reference="type_employment">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField>
                    <NumberField source="salary" />
                </SimpleShowLayout>
            </Show>
        )


        return (
            <Show disableAuthentication>
                <SimpleShowLayout>
                    <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                    <RichTextField label="resources.vacancies.fields.description" source={`description.${locale}`} />
                    <ReferenceField source="scheduleId" reference="schedules">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField>
                    <ReferenceField source="typeEmploymentId" reference="type_employment">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField>
                    <NumberField source="salary" />
                    <Interviews permissions={permissions}/>
                </SimpleShowLayout>
            </Show>
        )
}

export default VacanciesShow