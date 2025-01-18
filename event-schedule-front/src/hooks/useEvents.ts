import { useState, useEffect } from 'react';
import Event from '../types/Event';
import axiosConfig from '../axiosConfig';

const useEvents = (activeFilter: boolean | null) => {
  const [events, setEvents] = useState<Event[]>([]);
  const [eventsLoading, setEventsLoading] = useState<boolean>(false);
  const [errorEvents, setErrorEvents] = useState<string | null>(null);

  const fetchEvents = async () => {
    setEventsLoading(true);
    setErrorEvents(null);
    try {
      let url = 'events';
      
      if (activeFilter !== null) {
        url += `?active=${activeFilter}`;
      }

      const response = await axiosConfig.get(url);
      setEvents(response.data);
    } catch (error) {
      setErrorEvents('Erro ao carregar os eventos');
      console.error(error);
    } finally {
      setEventsLoading(false);
    }
  };

  useEffect(() => {
    fetchEvents();
  }, [activeFilter]);

  return { events, eventsLoading, errorEvents };
};

export default useEvents;
