import { Categorie } from './categorie';
import { Image } from './image';

export class Produit {
    constructor(public id: number,
                public nom: string,
                public prix: number,
                public poids: number,
                public categorie?: Categorie,
                public images?: Image[]){}
}