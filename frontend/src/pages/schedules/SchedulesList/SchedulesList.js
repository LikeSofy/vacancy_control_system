import { List, Datagrid, TextField, useLocaleState } from 'react-admin';

const SchedulesList = () => {
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

export default SchedulesList