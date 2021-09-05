import { Item } from "./item";
import { Customer } from "./customer";

export class Order {

    idOrder: number | undefined;
    totalPrice: number | undefined;
    status: string | undefined;
    items: Array<Item> | undefined;
    customer: Customer | undefined;

}
