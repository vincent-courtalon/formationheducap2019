export class Picture {
    constructor(public id: number,
                public titre: string,
                public dateAdded: Date,
                public fileName: string,
                public contentType: string,
                public width: number,
                public height: number) {}
}