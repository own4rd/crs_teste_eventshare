import {
    Table,
    TableBody,
    TableCell,
    TableContainer,
    TableHead,
    TableRow,
    Paper,
  } from '@mui/material';
import Event from '../../../types/Event';

export default function EventTable({ events }: { events: Event[] }) {
    return (

        <TableContainer component={Paper} sx={{ marginTop: 2 }}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell><strong>Nome do Evento</strong></TableCell>
                        <TableCell><strong>Data de Início</strong></TableCell>
                        <TableCell><strong>Data de Término</strong></TableCell>
                        <TableCell><strong>Instituição</strong></TableCell>
                        <TableCell><strong>Status</strong></TableCell>
                    </TableRow>
                </TableHead>
                <TableBody>
                    {events.length === 0 ? (
                        <TableRow>
                            <TableCell colSpan={5} align="center">Nenhum evento encontrado.</TableCell>
                        </TableRow>
                    ) : (
                        events.map((event) => (
                            <TableRow key={event.id}>
                                <TableCell>{event.name}</TableCell>
                                <TableCell>{event.startDate}</TableCell>
                                <TableCell>{event.endDate}</TableCell>
                                <TableCell>{event.institutionName}</TableCell>
                                <TableCell>{event.status ? 'Ativo' : 'Inativo'}</TableCell>
                            </TableRow>
                        ))
                    )}
                </TableBody>
            </Table>
        </TableContainer>
    );
}