import { List, Datagrid,  TextField, EmailField, SelectField } from 'react-admin';

const UsersList = () => (
    <List>
        <Datagrid rowClick="edit">
            <TextField source="id" />
            <TextField source="name" />
            <TextField source="surname" />
            <EmailField  source="email" />
            <SelectField source="role" choices={[
                { id: 'ROLE_USER', name: 'resources.users.fields.roleEnum.user' },
                { id: 'ROLE_DIRECTOR', name: 'resources.users.fields.roleEnum.director' },
                { id: 'ROLE_ADMINISTRATOR', name: 'resources.users.fields.roleEnum.admin' },
                { id: 'ROLE_INTERVIEWER', name: 'resources.users.fields.roleEnum.interviewer' },
            ]}/>
        </Datagrid>
    </List>
);

export default UsersList