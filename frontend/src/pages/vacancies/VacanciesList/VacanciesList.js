import { 
    List, 
    Datagrid, 
    TextField, 
    ReferenceField, 
    useLocaleState, 
    NumberField, 
    usePermissions,
    ReferenceArrayInput,
    FilterForm,
    AutocompleteArrayInput
} from 'react-admin';
import { Card, CardContent } from '@mui/material';


const postFilters = [
    <ReferenceArrayInput label="resources.vacancies.fields.schedule" source="schedulesIdIn" reference="schedules" alwaysOn>
        <AutocompleteArrayInput label="resources.vacancies.fields.schedule" />
    </ReferenceArrayInput>,
    <ReferenceArrayInput label="resources.vacancies.fields.typeEmployment" source="typeEmploymentIdIn" reference="type_employment" alwaysOn>
        <AutocompleteArrayInput label="resources.vacancies.fields.typeEmployment" />
    </ReferenceArrayInput>,
];

export const FilterSidebar = () => (
    <Card sx={{ order: -1, mr: 2, mt: 8, width: "300px" }}>
        <CardContent>
            <FilterForm filters={postFilters} />
        </CardContent>
    </Card>
);

const VacanciesList = () => {
    const [locale] = useLocaleState();
    const { isLoading, permissions } = usePermissions()

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_ADMINISTRATOR")
        return (
            <List aside={<FilterSidebar />}>
                <Datagrid rowClick="edit">
                    <TextField source="id" />
                    <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                    <ReferenceField label="resources.vacancies.fields.schedule"  reference="schedules" source="scheduleId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <ReferenceField  label="resources.vacancies.fields.typeEmployment" reference="type_employment" source="typeEmploymentId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <NumberField source="salary" />
                </Datagrid>
            </List>
        )

    if (permissions === "ROLE_DIRECTOR")
        return (
            <List aside={<FilterSidebar />}>
                <Datagrid rowClick="edit">
                    <TextField source="id" />
                    <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                    <ReferenceField label="resources.vacancies.fields.schedule"  reference="schedules" source="scheduleId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <ReferenceField  label="resources.vacancies.fields.typeEmployment" reference="type_employment" source="typeEmploymentId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <NumberField source="salary" />
                </Datagrid>
            </List>
        )

    if (permissions === "ROLE_INTERVIEWER")
        return (
            <List aside={<FilterSidebar />}>
                <Datagrid rowClick="show" bulkActionButtons={null}>
                    <TextField source="id" />
                    <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                    <ReferenceField label="resources.vacancies.fields.schedule" reference="schedules" source="scheduleId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <ReferenceField label="resources.vacancies.fields.typeEmployment" reference="type_employment" source="typeEmploymentId">
                        <TextField source={`name.${locale}`} />
                    </ReferenceField >
                    <NumberField source="salary" />
                </Datagrid>
            </List>
        )

    
    return (
        <List aside={<FilterSidebar />} disableAuthentication>
            <Datagrid rowClick="show" bulkActionButtons={null}>
                <TextField source="id" />
                <TextField label="resources.vacancies.fields.name" source={`name.${locale}`} />
                <ReferenceField label="resources.vacancies.fields.schedule" reference="schedules" source="scheduleId">
                    <TextField source={`name.${locale}`} />
                </ReferenceField >
                <ReferenceField label="resources.vacancies.fields.typeEmployment" reference="type_employment" source="typeEmploymentId">
                    <TextField source={`name.${locale}`} />
                </ReferenceField >
                <NumberField source="salary" />
            </Datagrid>
        </List>
    )
};

export default VacanciesList