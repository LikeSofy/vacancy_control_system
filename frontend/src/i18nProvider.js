import polyglotI18nProvider from 'ra-i18n-polyglot';
import englishMessages from 'ra-language-english';
import russianMessages from 'ra-language-russian';

const newEnglishMessages = {
    ...englishMessages,
    usermenu: {
        login: "Log in"
    },
    ra: {
        ...englishMessages.ra,
        configurable: {
            customize: "Customize"
        }
    },
    login: {
        login: "Login",
        signIn: "Sign in",
        signUp: "Sign up",
        registrationInvitation: "Don’t have an account?",
        signInError: "Wrong login or password",
        form: {
            email: {
                label: "Email",
                
                    email: "Invalid email",
                    maxLength: "The email must be shorter than 60 character."
                
            },
            password: {
                label: "Password",
                errors: {
                    regex: "Password must be entered a letter and numbers and be longer than 8 characters and shorter than 16.",
                }
            },
        }
    },
    registration: {
        registration: "Registration",
        signIn: "Sign in",
        signUp: "Sign up",
        loginInvitation: "Have an account?",
        form: {
            name: {
                label: "Name",
                regex: "Invalid name",
                maxLength: "Name must be shorter than 30 character."
                
            },
            surname: {
                label: "Surname",
                regex: "Invalid surname",
                maxLength: "Surname must be shorter than 30 character."
                
            },
            email: {
                label: "Email",
                email: "Invalid email",
                maxLength: "The email must be shorter than 60 character."
            },
            password: {
                label: "Password",
                errors: {
                    regex: "Password must be entered a letter and numbers and be longer than 8 characters and shorter than 16.",
                }
            },
            replyPassword: {
                label: "Reply password",
                errors: {
                    passwordsMismatch: "Passwords mismatch",
                }
            },
        }
    },
    resources: {
        users: {
            name: 'User |||| Users',
            fields: {
                name: 'Name',
                surname: 'Surname',
                status: 'Status',
                password: "Password",
                role: 'Role',
                roleEnum: {
                    user: "User",
                    director: "Director",
                    admin: "Admin",
                    interviewer: "Interviewer"
                },
            },
        },
        type_employment: {
            name: 'Type employment |||| Types employment',
            fields: {
                name: 'Name',
                type: 'Type',
            }
        },
        schedules: {
            name: 'Schedule |||| Schedules',
            fields: {
                name: 'Name',
                type: 'Type',
            }
        },
        vacancies: {
            name: 'Vacancy |||| Vacancies',
            tabs: {
                common: "Common",
                report: "Report"
            },
            fields: {
                scheduleId: "Schedule",
                typeEmploymentId: "Type employment",
                description: "Description",
                name: 'Name',
                schedule: 'Schedule',
                typeEmployment: 'Type employment',
                salary: 'Salary',
                interviews: "Interviews"
            }
        },
        interviews: {
            name: 'Interview |||| Interviews',
            tabs: {
                common: "Common",
                report: "Report"
            },
            buttons: {
                subscribe: "Subscribe",
                unsubscribe: "Unsubscribe",
            },
            fields: {
                start: 'Start',
                interviewerId: 'Interviewer',
                userId: 'User',
                vacancyId: 'Vacancy',
                startFrom: "From",
                startTo: "To",
                report: {
                    grade: "Grade",
                    text: "Description"
                }
            }
        }
    },
};

const newRussianMessages = {
    ...russianMessages,
    usermenu: {
        login: "Войти"
    },
    ra: {
        ...russianMessages.ra,
        configurable: {
            customize: "Кастомизировать"
        }
    },
    login: {
        login: "Вход",
        signIn: "Вход",
        signUp: "Регистрация",
        registrationInvitation: "Еще не зарегестрированы?",
        signInError: "Неверный логин или пароль",
        form: {
            email: {
                label: "Email",
                errors: {
                    email: "Неправильный email",
                    maxLength: "Email должен быть короче 60 символов"
                }
            },
            password: {
                label: "Пароль",
                errors: {
                    regex: "Пароль должен состоять из цифр и букв, быть длинее 8 символов и короче 16",
                }
            },
        }
    },
    registration: {
        registration: "Регистрация",
        signIn: "Войти",
        signUp: "Зарегестрироваться",
        loginInvitation: "Уже есть аккаунт?",
        form: {
            name: {
                label: "Имя",
                regex: "Некоректное имя",
                maxLength: "Имя должно быть короче 60 символов."
                
            },
            surname: {
                label: "Фамилия",
                regex: "Некоректная фамилия",
                maxLength: "Фамилия должно быть короче 60 символов."
                
            },
            email: {
                label: "Email",
                email: "Неправильный email",
                maxLength: "Email должен быть короче 60 символов."
            },
            password: {
                label: "Пароль",
                errors: {
                    regex: "Пароль должен состоять из цифр и букв, быть длинее 8 символов и короче 16",
                }
            },
            replyPassword: {
                label: "Повторите пароль",
                errors: {
                    passwordsMismatch: "Пароли не совпадают",
                }
            },
        }
    },
    resources: {
        users: {
            name: 'Пользователь |||| Пользователи',
            fields: {
                name: 'Имя',
                surname: 'Фамилия',
                status: 'Статус',
                password: "Пароль",
                statusEnum: {
                    active: "Активирован",
                    blocked: "Заблокирован"
                },
                role: 'Роль',
                roleEnum: {
                    user: "Пользователь",
                    director: "Директор",
                    admin: "Администратор",
                    interviewer: "Интервьювер"
                },
            },
        },
        type_employment: {
            name: 'Тип занятости |||| Типы занятости',
            fields: {
                name: 'Название',
                type: 'Тип',
            }
        },
        schedules: {
            name: 'График работы |||| Графики работы',
            fields: {
                name: 'Название',
                type: 'Тип'
            }
        },
        vacancies: {
            name: 'Вакансия |||| Вакансии',
            tabs: {
                common: "Общая",
                report: "Отчет"
            },
            fields: {
                scheduleId: "Расписание",
                typeEmploymentId: "Тип занятости",
                description: "Описание",
                name: 'Название',
                schedule: 'Расписание',
                typeEmployment: 'Тип занятости',
                salary: 'Зарплата',
                interviews: "Интервью"
            }
        },
        interviews: {
            name: 'Интервью |||| Интервью',
            tabs: {
                common: "Общая",
                report: "Отчет"
            },
            buttons: {
                subscribe: "Записаться",
                unsubscribe: "Отменить запись",
            },
            fields: {
                start: 'Начало',
                interviewerId: 'Интервьювьер',
                userId: 'Пользователь',
                vacancyId: 'Вакансия',
                startFrom: "C",
                startTo: "По",
                report: {
                    grade: "Оценка",
                    text: "Описание"
                }
            }
        }
    },
};

export const i18nProvider =  polyglotI18nProvider(locale => 
    locale === 'ru' ? newRussianMessages : newEnglishMessages,
    'en' // Default locale
);