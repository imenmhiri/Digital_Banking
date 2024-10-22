import { Client } from "./client";

export class CompteBancaire {

id !: number;
datedecreation !: Date ;
solde !: number ; 
etat !: string ;
client!: Client;
decouvert !: number;
tauxInteret !: number;
}
