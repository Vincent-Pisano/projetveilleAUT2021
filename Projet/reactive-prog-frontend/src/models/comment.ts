import { Item } from "./item";
import { Customer } from "./customer";

export class Comment {

    idComment: number | undefined;
    comment: string;
    item: Item;
    customer: Customer;

    constructor() {
        this.comment = "";
        this.item = new Item();
        this.customer = new Customer();
    }

}
