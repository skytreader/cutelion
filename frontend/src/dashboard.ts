import { html } from 'lit';
import {BaseTemplate} from "Frontend/src/base-template";
import {Project} from "Frontend/src/data-model";

class Dashboard extends BaseTemplate {
    private projects: Array<Project> = [];

    protected override pageContent() {
        return html`
        <h2>Project Dashboard</h2>
        <p ?hidden="${this.projects.length != 0}">No projects found. <vaadin-button theme="primary" id="start-project">+ Start New Project</vaadin-button></p>
         <vaadin-horizontal-layout theme="spacing padding" style="justify-content: end" ?hidden="${this.projects.length == 0}">
           <vaadin-button theme="primary" id="start-project">+ New</vaadin-button>
           <vaadin-button theme="primary error" id="delete-project">- Delete</vaadin-button>
         </vaadin-horizontal-layout>
         <vaadin-list-box style="border: 1px solid #edf2f4" id="project-list">
         </vaadin-list-box>
        `;
    }
}

customElements.define("dashboard-page", Dashboard);