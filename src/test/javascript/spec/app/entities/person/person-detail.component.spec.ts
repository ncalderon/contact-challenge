/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { ContactchallengeTestModule } from '../../../test.module';
import { PersonDetailComponent } from 'app/entities/person/person-detail.component';
import { Person } from 'app/shared/model/person.model';

describe('Component Tests', () => {
    describe('Person Management Detail Component', () => {
        let comp: PersonDetailComponent;
        let fixture: ComponentFixture<PersonDetailComponent>;
        const route = ({ data: of({ person: new Person(123) }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [ContactchallengeTestModule],
                declarations: [PersonDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(PersonDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(PersonDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.person).toEqual(jasmine.objectContaining({ id: 123 }));
            });
        });
    });
});
