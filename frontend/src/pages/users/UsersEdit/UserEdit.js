import * as React from "react";
import {
    Edit,
    SimpleForm,
    SelectInput,
    TextInput,
    required,
    regex,
    maxLength,
    email
} from 'react-admin';

const UserEdit = () => (
    <Edit>
        <SimpleForm>
            <TextInput 
                source="name" 
                style={{width: '100%'}} 
                validate={[
                    required(),
                    regex(/^[a-zA-Zа-яА-Я0-9-_]*$/, "registration.form.name.regex"),
                    maxLength(30, "registration.form.name.maxLength")
                ]} 
            />
            <TextInput 
                source="surname" 
                style={{width: '100%'}} 
                validate={[
                    required(),
                    regex(/^[a-zA-Zа-яА-Я0-9-_]*$/, "registration.form.surname.regex"),
                    maxLength(30, "registration.form.surname.maxLength")
                ]}
            />
            <TextInput 
                source="email" 
                style={{width: '100%'}} 
                validate={[
                    email('login.form.email.errors.email'), 
                    maxLength(60, 'login.form.email.errors.maxLength')
                ]} 
            />
            <SelectInput label="resources.users.fields.role" source="role" validate={required()} style={{width: '100%'}} choices={[
                { id: 'ROLE_USER', name: 'resources.users.fields.roleEnum.user' },
                { id: 'ROLE_DIRECTOR', name: 'resources.users.fields.roleEnum.director' },
                { id: 'ROLE_ADMINISTRATOR', name: 'resources.users.fields.roleEnum.admin' },
                { id: 'ROLE_INTERVIEWER', name: 'resources.users.fields.roleEnum.interviewer' },
            ]} />
            <TextInput 
                style={{width: '100%'}} 
                source="password" 
                validate={[
                    regex(/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}/, "registration.form.password.errors.regex"),
                ]}
            />
        </SimpleForm>
    </Edit>
)

export default UserEdit