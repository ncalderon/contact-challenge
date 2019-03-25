/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ContactchallengeTestModule } from '../../../test.module';
import { PersonContactDetailComponent } from 'app/entities/person-contact/person-contact-detail.component';
import { PersonContact } from 'app/shared/model/person-contact.model';

describe('Component Tests', () => {
    describe('PersonContact Management Detail Component', () => {
        let comp: PersonContactDetailComponent;
        let fixture: ComponentFixture<PersonContactDetailComponent>;
        const route = ({ data: of({ personContact: new PersonContact(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [PersonContactDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PersonContactDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonContactDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.personContact).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
