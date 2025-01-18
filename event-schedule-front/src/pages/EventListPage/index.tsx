import React, { useState } from 'react';
import {
    Container,
    Switch,
    FormControlLabel,
    Typography,
    CircularProgress,
    Alert
} from '@mui/material';
import EventTable from './components/EventTable';
import useEvents from '../../hooks/useEvents';

const EventListPage: React.FC = () => {
    const [showActive, setShowActive] = useState<boolean>(true);

    const { events, eventsLoading, errorEvents } = useEvents(showActive);

    const handleToggleChange = (event: React.ChangeEvent<HTMLInputElement>) => {
        setShowActive(event.target.checked);
    };

    return (
        <Container maxWidth="xl" sx={{ marginTop: 5 }}>
            <Typography variant="h4" sx={{ marginBottom: 2 }}>
                Listagem de Eventos
            </Typography>

            {errorEvents && <Alert severity="error">{errorEvents}</Alert>}

            {eventsLoading ? (
                <CircularProgress />
            ) : (
                <>
                    <FormControlLabel
                        control={
                            <Switch
                                checked={showActive}
                                onChange={handleToggleChange}
                                name="activeToggle"
                                color="primary"
                            />
                        }
                        label={showActive ? 'Exibir Eventos Ativos' : 'Exibir Eventos Inativos'}
                    />

                    <EventTable events={events} />
                </>
            )}
        </Container>
    );
};

export default EventListPage;
