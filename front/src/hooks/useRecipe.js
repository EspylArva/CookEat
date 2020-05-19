import { useState, useEffect } from 'react';
import cookeatDb from '../indexedDb/cookeatDb';

function useRecipe(id) {
    const [recipe, setRecipe] = useState([]);
    const [loading, setLoading] = useState(false);
    const [offlineloading, setOfflineLoading] = useState(false);
    const [error, setError] = useState(undefined);
    const [online, setOnline] = useState(false);

    async function loadOnline() {
        setLoading(true);
        try {
            setRecipe(await cookeatDb[table].get(id))
            setLoading(false);
            setError(undefined);
        } catch (e) {
            setLoading(false);
            setError(e);
        }
    }

    async function loadOffline() {
        setOfflineLoading(true);
        try {
            // Offline first
            setRecipe(await cookeatDb[table].get(id))
            setOfflineLoading(false);
            setError(undefined);
        } catch (e) {
            setOfflineLoading(false);
            setError(e);
        } finally {
            loadOnline();
        }
    }
}

export default;