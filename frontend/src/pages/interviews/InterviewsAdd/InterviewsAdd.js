import * as React from "react";
import {
    SimpleForm,
    Create,
    ReferenceInput,
    DateTimeInput,
    usePermissions,
    required
} from 'react-admin';

const InterviewsAdd = props => {
    const { isLoading, permissions } = usePermissions()

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_INTERVIEWER")
        return (
            <Create {...props}>
                <SimpleForm>
                    <DateTimeInput source="start" validate={required}/>
                    <ReferenceInput source="userId" reference="users" filter={{ role: 'ROLE_USER' }} style={{width: "100%"}}/>
                    <ReferenceInput source="vacancyId" validate={required} reference="vacancies" style={{width: "100%"}}/>
                </SimpleForm>
            </Create >
        )
    
    return (
        <Create {...props}>
            <SimpleForm>
                <DateTimeInput source="start" validate={required}/>
                <ReferenceInput source="interviewerId" validate={required} reference="users" filter={{ role: 'ROLE_INTERVIEWER' }} style={{width: "100%"}} />
                <ReferenceInput source="userId" reference="users" filter={{ role: 'ROLE_USER' }} style={{width: "100%"}}/>
                <ReferenceInput source="vacancyId" validate={required} reference="vacancies" style={{width: "100%"}}/>
            </SimpleForm>
        </Create >
    )
}

export default InterviewsAdd