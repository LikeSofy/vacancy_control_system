import {useState} from "react";
import {Link, useLocation} from "react-router-dom";
import {email, Form, maxLength, regex, required, TextInput, useNotify, useLogin, useTranslate, useRefresh} from "react-admin";
import Box from '@mui/material/Box';
import LoginIcon from '@mui/icons-material/Login';
import {
    Avatar, Button, Card, CardActions, CircularProgress,
} from '@mui/material';

const Login = () => {
    const [loading, setLoading] = useState(false);
    const translate = useTranslate();
    const refresh = useRefresh();

    const notify = useNotify();
    const login = useLogin();
    const location = useLocation();

    const handleSubmit = (auth) => {
        setLoading(true)
        login(auth, location.state ? location.state.nextPathname : '/')
            .then(data => {
                setLoading(false)
                refresh()
            })
            .catch((error) => {
                setLoading(false)
                notify(typeof error === 'string' ? error : typeof error === 'undefined' || !error.message ? 'login.signInError' : error.message, {
                    type: 'warning', messageArgs: {
                        _: typeof error === 'string' ? error : error && error.message ? error.message : undefined,
                    },
                });
            });
    }

    return (<Form onSubmit={handleSubmit} noValidate>
        <Box
            sx={{
                display: 'flex',
                flexDirection: 'column',
                minHeight: '100vh',
                alignItems: 'center',
                justifyContent: 'flex-start',
                background: 'url(https://source.unsplash.com/random/1600x900)',
                backgroundRepeat: 'no-repeat',
                backgroundSize: 'cover',
            }}
        >
            <Card sx={{minWidth: 600, marginTop: '6em'}}>
                <Box
                    sx={{
                        margin: '1em', display: 'flex', justifyContent: 'center',
                    }}
                >
                    <Avatar sx={{bgcolor: 'secondary.main'}}>
                        <LoginIcon/>
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
                    {translate('login.login')}
                </Box>
                <Box sx={{padding: '0 1em 1em 1em'}}>
                    <Box>
                        <TextInput
                            autoFocus
                            source="email"
                            label={'login.form.email.label'}
                            disabled={loading}
                            validate={[email('login.form.email.errors.email'), maxLength(60, 'login.form.email.errors.maxLength')]}
                            fullWidth
                        />
                    </Box>
                    <Box>
                        <TextInput
                            source="password"
                            label={'login.form.password.label'}
                            type="password"
                            disabled={loading}
                            validate={[required(), regex(/(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[A-Za-z0-9]{8,16}/, "login.form.password.errors.regex"),]}
                            fullWidth
                        />
                    </Box>
                    <Box>
                        {translate('login.registrationInvitation')} <Link to={`../registration`}>{translate('login.signUp')}</Link>
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
                        {loading && (<CircularProgress size={25} thickness={2}/>)}
                        {translate('login.signIn')}
                    </Button>
                </CardActions>
            </Card>
        </Box>
    </Form>);
};

export default Login;