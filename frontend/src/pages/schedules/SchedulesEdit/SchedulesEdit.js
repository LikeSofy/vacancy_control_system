import * as React from "react";
import styles from "./ScheduleEdit.module.css"
import {
    Edit,
    SimpleForm,
    TextInput,
    useLocaleState,
    TranslatableInputs,
    required
} from 'react-admin';

const SchedulesEdit = () => {
    const [locale] = useLocaleState();

    return (
    <Edit>
        <SimpleForm>
            <TranslatableInputs 
                locales={['en', 'ru']} 
                defaultLocale={locale} 
                className={styles.Tabs}
            >
                <TextInput source="name" validate={required}/>
            </TranslatableInputs>
        </SimpleForm>
    </Edit>
)}

export default SchedulesEdit