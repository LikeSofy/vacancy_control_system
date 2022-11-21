import * as React from "react";
import styles from "./ScheduleAdd.module.css"
import {
    SimpleForm,
    TextInput,
    Create,
    TranslatableInputs,
    useLocaleState,
    required
} from 'react-admin';

const SchedulesAdd = props => {
    const [locale] = useLocaleState();

    return (
    <Create {...props}>
        <SimpleForm>
            <TranslatableInputs 
                locales={['en', 'ru']} 
                defaultLocale={locale}
                className={styles.Tabs}
            >
                <TextInput source="name" validate={required} style={{width: '100%'}}/>
            </TranslatableInputs>
        </SimpleForm>
    </Create >
)}

export default SchedulesAdd