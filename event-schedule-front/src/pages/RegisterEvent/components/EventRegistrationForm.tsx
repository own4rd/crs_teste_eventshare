import React, { useState } from 'react';
import {
  Box,
  TextField,
  Button,
  Select,
  MenuItem,
  InputLabel,
  FormControl,
  Container,
  FormHelperText,
  Typography
} from '@mui/material';
import { useForm, Controller } from 'react-hook-form';
import useInstitutions from '../../../hooks/useInstitutions';
import { useEventRegistration } from '../../../hooks/useEventRegistration';
import IEventForm from '../../../types/IEventForm';

const EventRegistrationForm: React.FC = () => {
  const { institutions } = useInstitutions();
  const { submitEvent, isLoading, error } = useEventRegistration();

  const [successMessage, setSuccessMessage] = useState<string | null>(null);

  const { control, handleSubmit, formState: { errors }, getValues, reset } = useForm<IEventForm>({
    defaultValues: {
      name: '',
      startDate: '',
      endDate: '',
      institutionId: undefined
    }
  });

  const onSubmit = async (data: IEventForm) => {
    setSuccessMessage(null);

    await submitEvent(data);

    if(error == null) {

      setSuccessMessage('Evento registrado com sucesso!');
      reset({
        name: '',
        startDate: '',
        endDate: '',
        institutionId: undefined
      });
    }
  };

  return (
    <Container maxWidth="sm" sx={{ display: 'flex', justifyContent: 'center', alignItems: 'center', minHeight: '100vh' }}>
      <Box
        component="form"
        onSubmit={handleSubmit(onSubmit)}
        sx={{
          display: 'flex',
          flexDirection: 'column',
          gap: 2,
          width: '100%',
          padding: 3,
          border: '1px solid #ddd',
          borderRadius: 2,
          boxShadow: 3
        }}
      >
        {successMessage && (
          <Typography variant="h6" color="success.main" sx={{ marginBottom: 2 }}>
            {successMessage}
          </Typography>
        )}

        {error && (
          <Typography variant="h6" color="error.main" sx={{ marginBottom: 2 }}>
            {error}
          </Typography>
        )}

        <Controller
          name="name"
          control={control}
          rules={{ required: 'Nome do evento é obrigatório.' }}
          render={({ field }) => (
            <TextField
              {...field}
              label="Nome do Evento"
              variant="outlined"
              error={!!errors.name}
              helperText={errors.name?.message}
            />
          )}
        />
        <Controller
          name="startDate"
          control={control}
          rules={{ required: 'Data de início é obrigatória.' }}
          render={({ field }) => (
            <TextField
              {...field}
              label="Data de Início"
              variant="outlined"
              type="date"
              InputLabelProps={{ shrink: true }}
              error={!!errors.startDate}
              helperText={errors.startDate?.message}
            />
          )}
        />
        <Controller
          name="endDate"
          control={control}
          rules={{
            required: 'Data de fim é obrigatória.',
            validate: (value) => {
              const startDate = getValues('startDate');
              if (startDate && new Date(value) <= new Date(startDate)) {
                return 'A data de fim deve ser posterior à data de início.';
              }
              return true;
            }
          }}
          render={({ field }) => (
            <TextField
              {...field}
              label="Data de Fim"
              variant="outlined"
              type="date"
              InputLabelProps={{ shrink: true }}
              error={!!errors.endDate}
              helperText={errors.endDate?.message}
            />
          )}
        />
        <FormControl variant="outlined" error={!!errors.institutionId}>
          <InputLabel id="institution-label">Instituição</InputLabel>
          <Controller
            name="institutionId"
            control={control}
            rules={{ required: 'Instituição é obrigatória.' }}
            render={({ field }) => (
              <Select
                {...field}
                label="Instituição"
                labelId="institution-label"
                fullWidth
              >
                {institutions.map((institution) => (
                  <MenuItem key={institution.id} value={institution.id}>
                    {institution.name}
                  </MenuItem>
                ))}
              </Select>
            )}
          />
          {errors.institutionId && <FormHelperText>{errors.institutionId?.message}</FormHelperText>}
        </FormControl>
        <Button variant="contained" type="submit" sx={{ marginTop: 2 }}>
          {isLoading ? 'Registrando...' : 'Registrar Evento'}
        </Button>
      </Box>
    </Container>
  );
};

export default EventRegistrationForm;
