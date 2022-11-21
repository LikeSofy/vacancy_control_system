import * as React from "react";
import styles from "./VacanciesEdit.module.css"
import {
    Datagrid,
    TextField,
    ReferenceField,
    ReferenceManyField,
    Edit,
    SimpleForm,
    TextInput,
    TabbedForm,
    FormTab,
    TranslatableInputs,
    ReferenceInput,
    NumberInput,
    useLocaleState,
    usePermissions,
    DateField,
    required
} from 'react-admin';
import { RichTextInput } from 'ra-input-rich-text';

const VacanciesEdit = () => {
    const { isLoading, permissions } = usePermissions()
    const [locale] = useLocaleState();

    if (isLoading)
        return (<div>Waiting for permissions...</div>)

    if (permissions === "ROLE_DIRECTOR")
        return (
            <Edit>
                <TabbedForm>
                    <FormTab label="resources.vacancies.tabs.common">
                        <TranslatableInputs
                            locales={['en', 'ru']}
                            defaultLocale={locale}
                            className={styles.Tabs}
                        >
                            <TextInput source="name" style={{width: "100%"}} validate={required}/>
                            <RichTextInput source="description" fullWidth validate={required}/>
                        </TranslatableInputs>
                        <ReferenceInput source="scheduleId" reference="schedules" validate={required} style={{width: "100%"}} />
                        <ReferenceInput source="typeEmploymentId" reference="type_employment" validate={required} style={{width: "100%"}}/>
                        <NumberInput source="salary" style={{width: "100%"}}/>
                    </FormTab>
                    <FormTab label="resources.vacancies.tabs.report">
                        <ReferenceManyField reference="interviews" target="vacancyId" label={false}>
                            <Datagrid bulkActionButtons={null} rowClick="show">
                                <TextField source="id" />
                                <DateField source="start" />
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
                                <TextField source="report.grade" />
                            </Datagrid>
                        </ReferenceManyField>
                    </FormTab>
                </TabbedForm>
            </Edit>
        )

    return (
        <Edit>
            <SimpleForm>
                <TranslatableInputs
                    locales={['en', 'ru']}
                    defaultLocale={locale}
                    className={styles.Tabs}
                >
                    <TextInput source="name" validate={required} style={{width: "100%"}}/>
                    <RichTextInput source="description" validate={required} fullWidth/>
                </TranslatableInputs>
                <ReferenceInput source="scheduleId" reference="schedules" validate={required} style={{width: "100%"}} />
                <ReferenceInput source="typeEmploymentId" reference="type_employment" validate={required} style={{width: "100%"}}/>
                <NumberInput source="salary" style={{width: "100%"}}/>
            </SimpleForm>
        </Edit>)
}

export default VacanciesEdit