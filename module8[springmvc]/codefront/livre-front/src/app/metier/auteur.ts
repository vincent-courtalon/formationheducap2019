import { Livre } from './livre';

export class Auteur {
    constructor (public id: number,
                public nom: string,
                public prenom: string,
                public livres? : Livre[]) {}
}