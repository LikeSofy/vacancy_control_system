import {useState} from "react";
import {Link} from "react-router-dom";
import {Form, required, regex, maxLength, email, TextInput, useNotify, useTranslate} from "react-admin";
import Box from '@mui/material/Box';
import HowToRegIcon from '@mui/icons-material/HowToReg';
import authProvider from '../../authProvider'
import { useNavigate } from "react-router-dom";
import {equalValidation} from '../../validators/EqualValidator'
import {
    Avatar,
    Button,
    Card,
    CardActions,
    CircularProgress,
} from '@mui/material';

const Registration = () => {
    const [loading, setLoading] = useState(false);
    const translate = useTranslate();
    const notify = useNotify();
    const navigate = useNavigate();

    const handleSubmit = (register) => {
        setLoading(true)
        authProvider.register(register)
            .then(data => {
                if (data.redirectToLogin) {
                    navigate("../login")
                }
            })
            .catch(error => {
                notify(
                    typeof error === 'string'
                        ? error
                        : typeof error === 'undefined' || !error.message
                            ? 'ra.auth.sign_in_error'
                            : error.message,
                    {
                        type: 'warning',
                        messageArgs: {
                            _:
                                typeof error === 'string'
                                    ? error
                                    : error && error.message
                                        ? error.message
                                        : undefined,
                        },
                    }
                );
                setLoading(false)
            })
    };

    return (
        <Form onSubmit={handleSubmit} noValidate disableAuthentication>
            <Box
                sx={{
                    display: 'flex',
                    flexDirection: 'column',
                    minHeight: '100vh',
                    alignItems: 'center',
                    justifyContent: 'flex-start',
                    background:
                        'url(https://source.unsplash.com/random/1600x900)',
                    backgroundRepeat: 'no-repeat',
                    backgroundSize: 'cover',
                }}
            >
                <Card sx={{minWidth: 600, marginTop: '6em'}}>
                    <Box
                        sx={{
                            margin: '1em',
                            display: 'flex',
                            justifyContent: 'center',
                        }}
                    >
                        <Avatar sx={{bgcolor: 'secondary.main'}}>
                            <HowToRegIcon/>
                        </Avatar>
                    </Box>
                    <Box
                        sx={{
                            marginTop: '1em',
                            display: 'flex',
                            justifyContent: 'center',
                            color: theme => theme.palette.grey[500],
                        }}
                    >
                        {translate('registration.registration')}
                    </Box>
                    <Box sx={{padding: '0 1em 1em 1em'}}>
                        <Box>
                            <TextInput
                                autoFocus
                                source="name"
                                label={'registration.form.name.label'}
                                disabled={loading}
                                validate={[
                                    required(),
                                    regex(/^[a-zA-Zа-яА-Я0-9-_]*$/, "registration.form.name.regex"),
                                    maxLength(30, "registration.form.name.maxLength")
                                ]}
                                fullWidth
                            />
                        </Box>
                        <Box>
                            <TextInput
                                autoFocus
                                source="surname"
                                label={'registration.form.surname.label'}
                                disabled={loading}
                                validate={[
                                    required(),
                                    regex(/^[a-zA-Zа-яА-Я0-9-_]*$/, "registration.form.surname.regex"),
                                    maxLength(30, "registration.form.surname.maxLength")
                                ]}
                                fullWidth
                            />
                        </Box>
                        <Box>
                            <TextInput
                                autoFocus
                                source="email"
                                label={'registration.form.email.label'}
                                disabled={loading}
                                validate={[
                                    required(),
                                    email("registration.form.email.email"),
                                    maxLength(60, "registration.form.email.maxLength")
                                ]}
                                fullWidth
                            />
                        </Box>
                        <Box>
                            <TextInput
                                source="password"
                                label={'registration.form.password.label'}
                                type="password"
                                disabled={loading}
                                validate={[
                                    required(),
                                    regex(/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}/, "registration.form.password.errors.regex"),
                                ]}
                                fullWidth
                            />
                        </Box>
                        <Box>
                            <TextInput
                                source="replyPassword"
                                label={'registration.form.replyPassword.label'}
                                type="password"
                                disabled={loading}
                                validate={[
                                    required(),
                                    equalValidation("registration.form.replyPassword.errors.passwordsMismatch", "password")
                                ]}
                                fullWidth
                            />
                        </Box>
                        <Box>
                        {translate('registration.loginInvitation')} <Link to={`../login`}>{translate('registration.signIn')}</Link>
                        </Box>
                    </Box>
                    <CardActions sx={{padding: '0 1em 1em 1em'}}>
                        <Button
                            variant="contained"
                            type="submit"
                            color="primary"
                            disabled={loading}
                            fullWidth
                        >
                            {loading && (
                                <CircularProgress size={25} thickness={2}/>
                            )}
                            {translate('registration.signUp')}
                        </Button>
                    </CardActions>
                </Card>
            </Box>
        </Form>
    );
};

export default Registration;