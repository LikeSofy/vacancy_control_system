import axios from "axios";

export const axiosInstance = axios.create({
    baseURL: "http://localhost:8080",
    paramsSerializer: {
        indexes: null // by default: false
    }
})

axiosInstance.interceptors.request.use(request => {
    let token = localStorage.getItem('token')
    if (token){
        request.headers["Authorization"] = "Bearer " + token
    }
    
    return request
})

export const dataProvider = {
    getList: (resource, params) => {
        let requestParams = {
            sortBy: params.sort.field,
            sortDirection: params.sort.order,
            pageNumber: params.pagination.page,
            pageSize: params.pagination.perPage,
            ...params.filter
        }
        console.log(requestParams)
        return axiosInstance.get(`/api/v1/${resource}`, {params: requestParams})
            .then((response) => {
                return response.data
            })
    },

    getOne: (resource, params) => {
        return axiosInstance.get(`/api/v1/${resource}/${params.id}`)
            .then((response) => {
                return {data: response.data}
            })
    },

    getMany: (resource, params) => {
        return axiosInstance.get(`/api/v1/${resource}?ids=${params.ids}`)
            .then((response) => {
                return {data: response.data}
            })
    },

    getManyReference: (resource, params) => {
        let requestParams = {
            sortBy: params.sort.field,
            sortDirection: params.sort.order,
            pageNumber: params.pagination.page,
            pageSize: params.pagination.perPage,
            ...params.filter
        }
        requestParams[params.target] = params.id
        return axiosInstance.get(`/api/v1/${resource}`, {params: requestParams})
           .then((response) => {
               return response.data
           })
    },

    create: (resource, params) => {
        return axiosInstance.post(`/api/v1/${resource}`, params.data)
            .then((response) => {
                return {data: response.data}
            })
    },

    update: (resource, params) => {
        delete params.data.id
        return axiosInstance.put(`/api/v1/${resource}/${params.id}`, params.data)
            .then((response) => {
                return {data: response.data}
            })
    },

    updateMany: (resource, params) => Promise.resolve(),

    delete: (resource, params) => {
        return axiosInstance.delete(`/api/v1/${resource}/${params.id}`)
            .then((response) => {
                return {data: params.data}
            })
    },

    deleteMany: (resource, params) => {
        return axiosInstance.delete(`/api/v1/${resource}?ids=${params.ids}`)
            .then((response) => {
                return {data: params.ids}
            })
    },
}