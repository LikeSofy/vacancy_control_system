import * as React from "react";
import styles from "./TypeEmploymentEdit.module.css"
import {
    useLocaleState,
    Edit,
    SimpleForm,
    TextInput,
    TranslatableInputs,
    required
} from 'react-admin';

const TypeEmploymentEdit = () => {
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

export default TypeEmploymentEdit