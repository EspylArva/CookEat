import Dexie from 'dexie';

const db = new Dexie('cookeat');
db.version(1).stores({
    basketRecipes: `id, designation, list_gallery, prep_time, total_price, list_ingredients, list_steps`
});

export default db;