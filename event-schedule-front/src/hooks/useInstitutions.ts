import { useState, useEffect } from 'react';
import axiosConfig from '../axiosConfig';
import Institution from '../types/Institution';

const useInstitutions = () => {
  const [institutions, setInstitutions] = useState<Institution[]>([]);
  const [loading, setLoading] = useState<boolean>(false);
  const [errorInstituicoes, setErrorInstituicoes] = useState<string | null>(null);

  const fetchInstitutions = async () => {
    setLoading(true);
    setErrorInstituicoes(null);
    try {
      const response = await axiosConfig.get('institutions');
      setInstitutions(response.data);
    } catch (error) {
      setErrorInstituicoes('Erro ao carregar as instituições');
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchInstitutions();
  }, []);

  return { institutions, loading, errorInstituicoes };
};

export default useInstitutions;
