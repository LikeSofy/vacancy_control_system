import * as React from "react";
import {
    Edit,
    DateTimeInput,
    TextInput,
    ReferenceInput,
    usePermissions,
    TabbedForm,
    FormTab,
    required
} from 'react-admin';
import { RichTextInput } from 'ra-input-rich-text';

const VacanciesEdit = () => {
    const { isLoading, permissions } = usePermissions()

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_INTERVIEWER")
        return (
            <Edit>
            <TabbedForm>
                <FormTab label="resources.interviews.tabs.common">
                    <DateTimeInput source="start" validate={required}/>
                    <ReferenceInput source="userId" reference="users" filter={{role: 'ROLE_USER'}} style={{width: "100%"}}/>
                    <ReferenceInput source="vacancyId" validate={required}reference="vacancies" style={{width: "100%"}}/>
                </FormTab>
                <FormTab label="resources.interviews.tabs.report">
                    <TextInput source="report.grade" fullWidth />
                    <RichTextInput source="report.text" fullWidth/>
                </FormTab>
                
            </TabbedForm>
        </Edit>
        )
    
    return (
        <Edit>
            <TabbedForm>
                <FormTab label="resources.interviews.tabs.common">
                    <DateTimeInput source="start" validate={required}/>
                    <ReferenceInput source="interviewerId" validate={required} reference="users" filter={{ role: 'ROLE_INTERVIEWER' }} style={{width: "100%"}} />
                    <ReferenceInput source="userId" reference="users" filter={{role: 'ROLE_USER'}} style={{width: "100%"}}/>
                    <ReferenceInput source="vacancyId" validate={required} reference="vacancies" style={{width: "100%"}}/>
                </FormTab>
                <FormTab label="resources.interviews.tabs.report">
                    <TextInput source="report.grade" fullWidth />
                    <RichTextInput source="report.text" fullWidth/>
                </FormTab>
                
            </TabbedForm>
        </Edit>
    )
}

export default VacanciesEdit