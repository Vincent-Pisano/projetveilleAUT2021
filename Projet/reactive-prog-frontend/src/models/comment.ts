import { Item } from "./item";
import { Customer } from "./customer";

export class Comment {

    idComment: number | undefined;
    comment: string | undefined;
    item: Item | undefined;
    customer: Customer | undefined;

}
