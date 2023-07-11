import { html, LitElement } from 'lit';

export abstract class BaseTemplate extends LitElement {

    abstract pageContent(): any;

    render() {
        return html`
        <div>
            <h1>CuteL10N</h1>
        </div>
        ${this.pageContent()}
        `
    }

}