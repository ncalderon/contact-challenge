import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IAddress } from 'app/shared/model/address.model';

type EntityResponseType = HttpResponse<IAddress>;
type EntityArrayResponseType = HttpResponse<IAddress[]>;

@Injectable({ providedIn: 'root' })
export class AddressService {
    public resourceUrl = SERVER_API_URL + 'api/addresses';

    constructor(protected http: HttpClient) {}

    create(address: IAddress): Observable<EntityResponseType> {
        return this.http.post<IAddress>(this.resourceUrl, address, { observe: 'response' });
    }

    update(address: IAddress): Observable<EntityResponseType> {
        return this.http.put<IAddress>(this.resourceUrl, address, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<IAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IAddress[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
