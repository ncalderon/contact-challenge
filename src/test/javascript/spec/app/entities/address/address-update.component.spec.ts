/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { ContactchallengeTestModule } from '../../../test.module';
import { AddressUpdateComponent } from 'app/entities/address/address-update.component';
import { AddressService } from 'app/entities/address/address.service';
import { Address } from 'app/shared/model/address.model';

describe('Component Tests', () => {
    describe('Address Management Update Component', () => {
        let comp: AddressUpdateComponent;
        let fixture: ComponentFixture<AddressUpdateComponent>;
        let service: AddressService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [AddressUpdateComponent]
            })
                .overrideTemplate(AddressUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(AddressUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(AddressService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new Address(123);
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.address = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new Address();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.address = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
