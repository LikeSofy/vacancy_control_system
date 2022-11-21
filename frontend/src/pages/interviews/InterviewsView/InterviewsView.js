import * as React from "react";
import {
    TextField,
    Show,
    RichTextField,
    SimpleShowLayout,
} from 'react-admin';

const InterviewsView = () => (
    <Show>
        <SimpleShowLayout>
            <TextField source="report.grade" />
            <RichTextField source="report.text" />
        </SimpleShowLayout>
    </Show>
)

export default InterviewsView