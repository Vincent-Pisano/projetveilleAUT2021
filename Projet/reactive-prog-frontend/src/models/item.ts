export class Item {

    idItem: number | undefined;
    name: string;
    price: number;
    type: string ;

    constructor() {
        this.name = "";
        this.price = 0.0;
        this.type = "";
    }

}
