import { useState, useEffect } from 'react';
import cookeatDb from '../indexedDb/cookeatDb';

function useCookeatDB(table) {
    const [basket, setBasket] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState(undefined);

    async function load() {
        setLoading(true);
        try {
            setBasket(await cookeatDb[table].toArray())
            setLoading(false);
            setError(undefined);
        } catch (e) {
            setLoading(false);
            setError(e);
        }
    }

    useEffect(() => {
        load();
    }, [])

    const actions = {
        reload: load
    }

    return [basket, loading, error, actions];
}

export default useCookeatDB;