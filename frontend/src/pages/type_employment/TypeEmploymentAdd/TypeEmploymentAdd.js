import * as React from "react";
import styles from "./TypeEmploymentAdd.module.css"
import {
    SimpleForm,
    TextInput,
    Create,
    TranslatableInputs,
    useLocaleState,
    required
} from 'react-admin';

const TypeEmploymentAdd = props => {
    const [locale] = useLocaleState();

    return (
    <Create {...props}>
        <SimpleForm>
            <TranslatableInputs 
                locales={['en', 'ru']} 
                defaultLocale={locale} 
                className={styles.Tabs}
            >
                <TextInput source="name" style={{width: '100%'}} validate={required}/>
            </TranslatableInputs>
        </SimpleForm>
    </Create >
)}

export default TypeEmploymentAdd