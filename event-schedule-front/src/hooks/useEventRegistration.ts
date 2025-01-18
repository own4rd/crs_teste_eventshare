import { useState } from 'react';
import { registerEvent } from '../services/EventService';
import IEventForm from '../types/IEventForm';

export const useEventRegistration = () => {
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState<string | null>(null);

    const submitEvent = async (data: IEventForm) => {
        setIsLoading(true);
        setError(null);
        try {
            const response = await registerEvent(data);
            return response;
        } catch (error: any) {
            const errors = JSON.parse(error.message); 
            setError(errors);
        } finally {
            setIsLoading(false);
        }
    };

    return { submitEvent, isLoading, error };
};
