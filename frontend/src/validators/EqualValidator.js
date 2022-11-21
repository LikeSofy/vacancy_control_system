export const equalValidation = (message, otherFieldKey) => (value, allValues) => {
    if (value !== allValues[otherFieldKey]) {
        return message;
    }

    return undefined;
};