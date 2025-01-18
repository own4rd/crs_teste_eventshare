import axiosConfig from '../axiosConfig';
import IEventForm from '../types/IEventForm';

export const registerEvent = async (data: IEventForm) => {
    try {
        const response = await axiosConfig.post('events', data);
        return response.data;
    } catch (error: any) {
        if (error.response && error.response.data && error.response.data.errors) {
            throw new Error(JSON.stringify(error.response.data.errors));
        }
        throw new Error('Erro ao registrar evento: ' + error.message);
    }
};
