import { html } from 'lit';
import {BaseTemplate} from "Frontend/src/base-template";

class Dashboard extends BaseTemplate {
    private projects: Array<any> = [];

    pageContent() {
        return this.projects.length == 0 ? html`
         <p>No projects found. <button id="start-project">Start one?</button></p>
         ` :
         html`
         <p>Some projects found placeholder.</p>
         `
    }
}

customElements.define("dashboard-page", Dashboard);