import { Produit } from './produit';

export class Image {
    constructor(public id: number,
                public nom: string,
                public fileName: string,
                public contentType: string,
                public produit?: Produit) {}
}