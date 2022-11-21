import { List, Datagrid, useLocaleState, TextField } from 'react-admin';

const TypeEmploymentList = () => {
    const [locale] = useLocaleState();

    return (
        <List>
            <Datagrid rowClick="edit">
                <TextField source="id" />
                <TextField label="resources.schedules.fields.type" source={`name.${locale}`} />
            </Datagrid>
        </List>
    )
};

export default TypeEmploymentList