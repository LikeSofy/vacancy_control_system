import './App.css';
import {Admin, CustomRoutes, Resource} from 'react-admin';
import { Route } from "react-router-dom";
import authProvider from "./authProvider";
import Registration from "./pages/registration/Registration";
import Login from "./pages/login/Login";
import UserList from "./pages/users/UsersList/UserList";
import UserEdit from "./pages/users/UsersEdit/UserEdit";
import SchedulesList from "./pages/schedules/SchedulesList/SchedulesList";
import SchedulesEdit from "./pages/schedules/SchedulesEdit/SchedulesEdit";
import SchedulesAdd from "./pages/schedules/ScheduleAdd/ScheduleAdd";
import TypeEmploymentList from "./pages/type_employment/TypeEmploymentList/TypeEmploymetList";
import TypeEmploymentEdit from "./pages/type_employment/TypeEmploymentEdit/TypeEmploymentEdit";
import TypeEmploymentAdd from "./pages/type_employment/TypeEmploymentAdd/TypeEmploymentAdd";
import VacanciesShow from "./pages/vacancies/VacansiesShow/VacanciesShow";
import VacanciesList from "./pages/vacancies/VacanciesList/VacanciesList";
import VacanciesEdit from "./pages/vacancies/VacanciesEdit/VacanciesEdit";
import VacanciesAdd from "./pages/vacancies/VacanciesAdd/VacanciesAdd";
import InterviewsList from "./pages/interviews/InterviewsList/InterviewsList";
import InterviewsEdit from "./pages/interviews/InterviewsEdit/InterviewsEdit";
import InterviewsAdd from "./pages/interviews/InterviewsAdd/InterviewsAdd";
import InterviewsView from "./pages/interviews/InterviewsView/InterviewsView";
import {dataProvider} from './dataProvider'
import {i18nProvider} from './i18nProvider'
import { LocalesMenuButton, AppBar, Layout, UserMenu, Logout, usePermissions, useTranslate} from 'react-admin';
import { Link } from 'react-router-dom';
import {
    MenuItem,
    ListItemIcon,
    ListItemText,
    Typography,
} from '@mui/material';
import LoginIcon from '@mui/icons-material/Login';

const MyAppBar = (props) => (
    <AppBar {...props} userMenu={<CustomUserMenu />}>
        <Typography flex="1" variant="h6" id="react-admin-title"></Typography>
        <LocalesMenuButton languages={[
            { locale: 'en', name: 'English' },
            { locale: 'ru', name: 'Russian' },
        ]} />
    </AppBar>
);

const CustomUserMenu = () => {
    const { isLoading, permissions } = usePermissions()
    const translate = useTranslate();

    if (isLoading || permissions === "ROLE_GUEST")
        return (
            <UserMenu>
                <MenuItem
                    component={Link}
                    to="/login"
                >
                    <ListItemIcon>
                        <LoginIcon />
                    </ListItemIcon>
                    <ListItemText>{translate('usermenu.login')}</ListItemText>
                </MenuItem>
            </UserMenu>
        )

    return (
        <UserMenu>
            <Logout />
        </UserMenu>
    )
}

const MyLayout = (props) => <Layout {...props} appBar={MyAppBar}/>;

function App() {
    return (
        <Admin 
            authProvider={authProvider} 
            dataProvider={dataProvider} 
            loginPage={Login} 
            i18nProvider={i18nProvider}
            layout={MyLayout}
        >
            {permissions => (
                <>
                    {permissions === 'ROLE_ADMINISTRATOR' &&
                        <>
                            <Resource name="users" recordRepresentation={(user) => `${user.name} ${user.surname}`} edit={UserEdit} list={UserList}/>
                            <Resource name="type_employment" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={TypeEmploymentList} edit={TypeEmploymentEdit} create={TypeEmploymentAdd}/>
                            <Resource name="schedules" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={SchedulesList} edit={SchedulesEdit} create={SchedulesAdd}/>
                            <Resource name="vacancies" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={VacanciesList} edit={VacanciesEdit} create={VacanciesAdd}/>
                            <Resource name="interviews" list={InterviewsList} edit={InterviewsEdit} create={InterviewsAdd}/>
                        </>
                    }
                    {permissions === 'ROLE_DIRECTOR' && 
                        <>
                            <Resource name="users" recordRepresentation={(user) => `${user.name} ${user.surname}`}/>
                            <Resource name="type_employment" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={TypeEmploymentList} edit={TypeEmploymentEdit} create={TypeEmploymentAdd}/>
                            <Resource name="schedules" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={SchedulesList} edit={SchedulesEdit} create={SchedulesAdd}/>
                            <Resource name="vacancies" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={VacanciesList} edit={VacanciesEdit} create={VacanciesAdd}/>
                            <Resource name="interviews" show={InterviewsView}/>
                        </>
                    }
                    {permissions === 'ROLE_INTERVIEWER' && 
                        <>
                            <Resource name="users" recordRepresentation={(user) => `${user.name} ${user.surname}`}/>
                            <Resource name="type_employment" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="schedules" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="vacancies" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={VacanciesList} show={VacanciesShow}/>
                            <Resource name="interviews" list={InterviewsList} edit={InterviewsEdit} create={InterviewsAdd}/>
                        </>
                    }
                    {permissions === 'ROLE_USER' && 
                        <>
                            <Resource name="users" recordRepresentation={(user) => `${user.name} ${user.surname}`}/>
                            <Resource name="type_employment" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="schedules" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="vacancies" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={VacanciesList} show={VacanciesShow}/>
                            <Resource name="interviews" list={InterviewsList}/>
                        </>
                    }
                    {permissions === 'ROLE_GUEST' && 
                        <>
                            <Resource name="users" recordRepresentation={(user) => `${user.name} ${user.surname}`}/>
                            <Resource name="type_employment" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="schedules" recordRepresentation={`name.${i18nProvider.getLocale()}`}/>
                            <Resource name="vacancies" recordRepresentation={`name.${i18nProvider.getLocale()}`} list={VacanciesList} show={VacanciesShow}/>
                            <Resource name="interviews"/>
                        </>
                    }
                    <CustomRoutes noLayout>
                        <Route path="/registration" element={<Registration/>}/>
                    </CustomRoutes>
                </>
            )}
        </Admin>
    );
}

export default App;
