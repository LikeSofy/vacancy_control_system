import * as React from "react";
import styles from "./VacanciesAdd.module.css"
import {
    SimpleForm,
    TextInput,
    Create,
    TranslatableInputs,
    ReferenceInput,
    NumberInput,
    useLocaleState,
    required
} from 'react-admin';
import { RichTextInput } from 'ra-input-rich-text';

const VacanciesAdd = props => {
    const [locale] = useLocaleState();

    return (
        <Create {...props}>
            <SimpleForm>
                <TranslatableInputs 
                    locales={['en', 'ru']} 
                    defaultLocale={locale}
                    className={styles.Tabs}
                >
                    <TextInput source="name" style={{width: "100%"}} validate={required}/>
                    <RichTextInput source="description" fullWidth validate={required}/>
                </TranslatableInputs>
                <ReferenceInput source="scheduleId" reference="schedules" style={{width: "100%"}} validate={required}/>
                <ReferenceInput source="typeEmploymentId" reference="type_employment" style={{width: "100%"}} validate={required}/>
                <NumberInput source="salary" style={{width: "100%"}}/>
            </SimpleForm>
        </Create >
    )
}

export default VacanciesAdd