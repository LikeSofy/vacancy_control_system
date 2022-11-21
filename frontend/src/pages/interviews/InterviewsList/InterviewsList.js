import { 
    List, 
    Datagrid, 
    TextField, 
    useLocaleState, 
    useTranslate, 
    ReferenceField, 
    usePermissions, 
    useRefresh, 
    useRecordContext, 
    DateField,
    FilterForm,
    DateTimeInput,
} from 'react-admin';
import {axiosInstance} from "../../../dataProvider"
import Button from '@mui/material/Button';
import { Card, CardContent } from '@mui/material';

const postFilters = [
    <DateTimeInput label="resources.interviews.fields.startFrom" source="startFrom" alwaysOn fullWidth/>,
    <DateTimeInput label="resources.interviews.fields.startTo" source="startTo" alwaysOn fullWidth/>,
];

export const FilterSidebar = () => (
    <Card sx={{ order: -1, mr: 2, mt: 8, width: "300px", height: "fit-content"}}>
        <CardContent>
            <FilterForm filters={postFilters} />
        </CardContent>
    </Card>
);

const UnsubscribeButton = (props) => {
    const refresh = useRefresh();
    const record = useRecordContext(props);
    const translate = useTranslate();

    const subscribe = (id) => {
        axiosInstance.get(`/api/v1/interviews/${id}/unsubscribe`)
            .then((response) => {
                refresh()
                return response.data
            })
    }

    return record ? <Button variant="text" style={{float: "right"}} onClick={() => subscribe(record.id)}>{translate("resources.interviews.buttons.unsubscribe")}</Button> : null;
}

const InterviewsList = () => {
    const { isLoading, permissions } = usePermissions()
    const [locale] = useLocaleState();

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_INTERVIEWER")
        return (
            <List aside={<FilterSidebar />} filter={{ me: true }}>
                <Datagrid rowClick="edit">
                    <TextField source="id" />
                    <DateField source="start" showTime/>
                    <ReferenceField reference="users" source="interviewerId">
                        <>
                            <TextField source="name" style={{marginRight: "10px"}}/>
                            <TextField source="surname" />
                        </>
                    </ReferenceField >
                    <ReferenceField reference="users" source="userId">
                        <>
                            <TextField source="name" style={{marginRight: "10px"}}/>
                            <TextField source="surname" />
                        </>
                    </ReferenceField >
                    <ReferenceField reference="vacancies" source="vacancyId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                </Datagrid>
            </List>
        )
    
    if (permissions === "ROLE_USER")
        return (
            <List aside={<FilterSidebar />} filter={{ me: true }}>
                <Datagrid rowClick="edit" bulkActionButtons={null}>
                    <TextField source="id" />
                    <DateField source="start" showTime/>
                    <ReferenceField reference="users" source="interviewerId">
                        <>
                            <TextField source="name" style={{marginRight: "10px"}}/>
                            <TextField source="surname" />
                        </>
                    </ReferenceField >
                    <ReferenceField reference="vacancies" source="vacancyId">
                        <>
                            <TextField source={`name.${locale}`} style={{marginRight: "10px"}}/>
                            <TextField source="surname" />
                        </>
                    </ReferenceField >
                    <UnsubscribeButton/>
                </Datagrid>
            </List>
        )

    return (
        <List aside={<FilterSidebar />}>
            <Datagrid rowClick="edit">
                <TextField source="id" />
                <DateField source="start" showTime/>
                <ReferenceField reference="users" source="interviewerId">
                    <>
                        <TextField source="name" style={{marginRight: "10px"}}/>
                        <TextField source="surname" />
                    </>
                </ReferenceField >
                <ReferenceField reference="users" source="userId">
                    <>
                        <TextField source="name" style={{marginRight: "10px"}}/>
                        <TextField source="surname" />
                    </>
                </ReferenceField >
                <ReferenceField reference="vacancies" source="vacancyId">
                    <TextField source={`name.${locale}`} />
                </ReferenceField >
            </Datagrid>
        </List>
    )
};

export default InterviewsList