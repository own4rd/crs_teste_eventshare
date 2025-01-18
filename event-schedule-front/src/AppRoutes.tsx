import { Routes, Route } from 'react-router-dom';
import App from './App';
import RegisterEventPage from './pages/RegisterEvent';

const AppRoutes = () => {
  return (
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="/register" element={<RegisterEventPage />} />
    </Routes>
  );
}

export default AppRoutes;
