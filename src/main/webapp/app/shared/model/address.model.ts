export const enum AddressType {
    MAIN = 'MAIN',
    HOME = 'HOME',
    WORK = 'WORK',
    OTHER = 'OTHER'
}

export interface IAddress {
    id?: number;
    type?: AddressType;
    country?: string;
    state?: string;
    city?: string;
    neighborhood?: string;
    street?: string;
    fullAddress?: string;
    personFullname?: string;
    personId?: number;
}

export class Address implements IAddress {
    constructor(
        public id?: number,
        public type?: AddressType,
        public country?: string,
        public state?: string,
        public city?: string,
        public neighborhood?: string,
        public street?: string,
        public fullAddress?: string,
        public personFullname?: string,
        public personId?: number
    ) {}
}
