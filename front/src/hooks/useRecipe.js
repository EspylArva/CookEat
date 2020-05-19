import { useState, useEffect } from 'react';
import cookeatDb from '../indexedDb/cookeatDb';
import { configs } from '../configs/configs';

function useRecipe(id) {
    const [recipe, setRecipe] = useState(undefined);
    const [loading, setLoading] = useState(false);
    const [offlineloading, setOfflineLoading] = useState(false);
    const [error, setError] = useState(undefined);
    const [online, setOnline] = useState(false);

    async function loadOnline() {
        setLoading(true);
        try {
            const response = await fetch(`${configs.apiUrl}/recipes/${id}`);

            if(response.status != 200) {
                throw new Error("The server didn't told us a bad news :'(");
            }

            const recipe = await response.json();
            setRecipe(recipe)
            setLoading(false);
            setError(undefined);
            setOnline(true);
        } catch (e) {
            setLoading(false);
            setError(e);
        }
    }

    async function loadOffline() {
        setOfflineLoading(true);
        try {
            // Offline first
            setRecipe(await cookeatDb.basketRecipes.get(id))
            setOfflineLoading(false);
            setError(undefined);
        } catch (e) {
            setOfflineLoading(false);
            setError(e);
        }
    }

    async function load() {
        await loadOffline();
        await loadOnline();
    }

    useEffect(() => {
        load();
    }, [])

    return [recipe, online, loading, offlineloading, error, load];
}

export default useRecipe