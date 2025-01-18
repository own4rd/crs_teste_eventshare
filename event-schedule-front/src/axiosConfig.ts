import axios from 'axios';

const axiosConfig = axios.create({
    baseURL: 'http://localhost:8080/event/api/v1/',
});

export default axiosConfig;
