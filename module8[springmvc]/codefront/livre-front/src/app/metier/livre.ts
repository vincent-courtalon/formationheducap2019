import { Auteur } from './auteur';

export class Livre {
    constructor(public id : number,
                public titre: string,
                public nbPages: number,
                public isbn: string,
                public dateSortie: Date,
                public auteur? : Auteur) {}
}