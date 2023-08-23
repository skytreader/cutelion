import { html, LitElement } from 'lit';
import {ProjectForDeletion} from "Frontend/src/data-model";

export class DeleteProjectsConfirmation extends LitElement {
    private projects: Array<ProjectForDeletion> = [];

    render() {
        return html`
        <p>Are you sure you want to delete the following projects:</p>
        
        <ul>
        ${this.projects.map((p) =>
            html`<li>${p.name}</li>`
        )}
        </ul>`;
    }
}

customElements.define("delete-projects-confirmation", DeleteProjectsConfirmation);