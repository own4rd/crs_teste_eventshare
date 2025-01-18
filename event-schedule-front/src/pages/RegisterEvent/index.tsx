import React from 'react';
import Header from '../../components/Header';
import EventRegistrationForm from './components/EventRegistrationForm';

const RegisterEventPage: React.FC = () => {
  return (
    <div>
      <Header />
      <EventRegistrationForm />
    </div>
  );
}

export default RegisterEventPage;
