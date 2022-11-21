import {isExpired} from "react-jwt";
import axios from "axios";

const authProvider = {
    login: ({email, password}) => {
        return axios.post('http://localhost:8080/api/v1/auth/login', {email: email, password: password})
            .then(
                (response) => {
                    localStorage.setItem('token', response.data);
                    return {redirectTo: "/"};
                }
            )
            .catch((error) => {
                throw new Error(error.response?.data?.error ?? "Connection error");
            })
    },
    register: ({name, surname, email, password}) => {
        return axios.post('http://localhost:8080/api/v1/auth/registration', {name, surname, email, password})
            .then(
                (response) => {
                    return {redirectToLogin: true};
                }
            )
            .catch((error) => {
                throw new Error(error.response?.data?.error ?? "Connection error");
            })

    },
    logout: () => {
        localStorage.removeItem('token');
        return Promise.resolve();
    },
    checkError: (error) => {
        console.log(error)
        if (error.response.status === 401 || error.response.status === 403) {
            localStorage.removeItem('token');
            return Promise.reject();
        }
        return Promise.resolve();
    },
    checkAuth: () => {
        let token = localStorage.getItem('token')
        return token && !isExpired(token)
            ? Promise.resolve()
            : Promise.reject();
    },
    getIdentity: () => Promise.resolve(),
    getPermissions: () => {
        let token = localStorage.getItem('token')
        
        if (!token){
            return Promise.resolve("ROLE_GUEST")
        }

        return axios.get('http://localhost:8080/api/v1/auth/info', {headers: {"Authorization": `Bearer ${localStorage.getItem('token')}`}})
            .then(
                (response) => {
                    return response.data.role
                }
            )
            .catch((error) => {
                if (error.response === 403){
                    localStorage.removeItem('token')
                }

                return Promise.resolve("ROLE_GUEST")
            })
    },
};

export default authProvider;